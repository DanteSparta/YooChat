package chat;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import storage.UserStorage;
import util.HTMLFilter;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zw531129 on 15/4/4
 * So, what's the fucking copyright?
 */
@ServerEndpoint(value = "/chatRoom")
public class ChatRoom {
    //  Logger for Chat
    private static final Log log = LogFactory.getLog(ChatRoom.class);

    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Set<ChatRoom> connections = new CopyOnWriteArraySet<>();

    private final String nickname;
    private Session session;


    public ChatRoom() {
        nickname = UserStorage.names.get(connectionIds.getAndIncrement());
    }


    @OnOpen
    public void start(Session session) {
        this.session = session;
        connections.add(this);
        String message = String.format("* %s %s", nickname, "has joined.");
        broadcast(message);
    }

    @OnClose
    public void end() {
        connections.remove(this);
        String message = String.format("* %s %s",
                nickname, "has disconnected.");
        broadcast(message);
    }

    @OnMessage
    public void incoming(String message) {
        // Never trust the client
        String filteredMessage = String.format("%s: %s",
                nickname, HTMLFilter.filter(message.toString()));
        broadcast(filteredMessage);
    }


    @OnError
    public void onError(Throwable t) throws Throwable {
        log.error("Chat Error: " + t.toString(), t);
    }


    private static void broadcast(String msg) {
        for (ChatRoom client : connections) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                log.debug("Chat Error: Failed to send message to client", e);
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
                String message = String.format("* %s %s",
                        client.nickname, "has been disconnected.");
                broadcast(message);
            }
        }
    }

}
