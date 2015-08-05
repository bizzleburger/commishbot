package com.backmassage.commishbot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ullink.slack.simpleslackapi.SlackMessageHandle;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;

@Service
public class CommishBot {

	private final static Logger log = LoggerFactory.getLogger(CommishBot.class);
	private final String auth_token = "xoxb-8341226887-cydkQGLSlafMolYxbHTPGnjz";

	@PostConstruct
	public void botStuff() {
		Map<String, ArrayList<String>> matchups = new HashMap<String, ArrayList<String>>();
		ArrayList<String> schedule = new ArrayList<String>();
		schedule.add("Chris J");
		schedule.add("Kevin");
		schedule.add("Max");
		schedule.add("Chandos");
		schedule.add("Stephen");
		schedule.add("Max");
		schedule.add("Chandos");
		schedule.add("Chris J");
		schedule.add("Josh");
		schedule.add("Kevin");
		schedule.add("Larry");
		schedule.add("Chris L");
		schedule.add("Adrienne");
		matchups.put("brandon", schedule);
		matchups.put("chris", schedule);

		SlackSession session = SlackSessionFactory.createWebSocketSlackSession(auth_token);
		session.addMessagePostedListener(new SlackMessagePostedListener() {
			@Override
			public void onEvent(SlackMessagePosted message, SlackSession session) {

				if (!message.getSender().isBot()) {
					if (message.getMessageContent().startsWith("$hello")) {
						session.sendMessage(session.findChannelByName("bot_testing"), "Hey man", null);
					}
					if (message.getMessageContent().startsWith("$matchup")) {
						log.debug("user " + message.getSender().getUserName());
						int week = Integer.parseInt(message.getMessageContent().split(" ")[1]);
						log.debug("week " + week);
						String match = matchups.get(message.getSender().getUserName()).get(week-1);
						session.sendMessage(session.findChannelByName("bot_testing"), match, null);
					}
				}

			}
		});
		try {
			session.connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		CommishBot bot = new CommishBot();
		bot.botStuff();
		
	}

}
