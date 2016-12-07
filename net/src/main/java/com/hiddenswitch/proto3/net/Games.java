package com.hiddenswitch.proto3.net;

import com.hiddenswitch.proto3.net.common.ClientConnectionConfiguration;
import com.hiddenswitch.proto3.net.common.MatchmakingRequest;
import com.hiddenswitch.proto3.net.common.MatchmakingResponse;
import com.hiddenswitch.proto3.net.models.*;
import com.hiddenswitch.proto3.net.util.Matchmaker;
import com.hiddenswitch.proto3.server.GameSession;
import com.hiddenswitch.proto3.server.PregamePlayerConfiguration;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.eventbus.impl.codecs.JsonObjectMessageCodec;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.sockjs.impl.JsonCodec;

import java.util.*;

public class Games extends Service<Games> {
	private GameSessions gameSessions;
	private Matchmaker matchmaker = new Matchmaker();
	private Map<String, ClientConnectionConfiguration> connections = new HashMap<>();

	@Override
	public void start(Future<Void> done) {
		if (gameSessions == null) {
			// TODO: Look up the verticle on the verticle service thing
			done.fail(new NullPointerException("gameSessions wasn't configured when the Games verticle was started. Please inject this dependency."));
		} else {
			EventBus eb = vertx.eventBus();
			// Configure the expire match method
			eb.consumer("Games::expireMatch", message -> {
				JsonObject body = (JsonObject) message.body();
				String gameId = body.getString("gameId");
				MatchExpireRequest request = new MatchExpireRequest();
				request.gameId = gameId;
				MatchExpireResponse response = expireMatch(request);
				JsonObject reply = new JsonObject();
				reply.put("expired", response.expired);
				message.reply(reply);
			}).completionHandler(then -> {
				done.complete();
			});
		}
	}

	@Override
	public void stop() {
		super.stop();
	}

	public void cancel(String userId) {
		matchmaker.remove(userId);
	}

	public MatchmakingResponse matchmakeAndJoin(MatchmakingRequest matchmakingRequest, String userId) {
		// TODO: Setup a user with a game against an AI if they've been waiting more than 10 seconds
		MatchmakingResponse response = new MatchmakingResponse();

		Matchmaker.Match match = matchmaker.match(userId, matchmakingRequest.deck);

		if (match == null) {
			response.setRetry(new MatchmakingRequest());
			return response;
		}

		if (getGameSessions().getGameSession(match.gameId) == null) {
			// Create a game session.
			CreateGameSessionResponse createGameSessionResponse = gameSessions.createGameSession(new CreateGameSessionRequest()
					.withPregame1(new PregamePlayerConfiguration(match.entry1.deck, match.entry1.userId))
					.withPregame2(new PregamePlayerConfiguration(match.entry2.deck, match.entry2.userId))
					.withGameId(match.gameId));
			GameSession session = createGameSessionResponse.toSession();
			connections.put(match.entry1.userId, session.getConfigurationForPlayer1());
			connections.put(match.entry2.userId, session.getConfigurationForPlayer2());
		}

		response.setConnection(connections.get(userId));
		return response;
	}

	public MatchExpireResponse expireMatch(MatchExpireRequest request) {
		final MatchExpireResponse response = new MatchExpireResponse();
		response.expired = matchmaker.expire(request.gameId);
		return response;
	}

	public GameSessions getGameSessions() {
		return gameSessions;
	}

	public void setGameSessions(GameSessions gameSessions) {
		this.gameSessions = gameSessions;
	}

	public Games withGameSessions(GameSessions gameSessions) {
		setGameSessions(gameSessions);
		return this;
	}
}
