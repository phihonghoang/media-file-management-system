import domainLogic.MediaUploadableMap;
import net.TCPServer;

public class Server {
    public static void main(String[] args) {
        MediaUploadableMap model = new MediaUploadableMap(1000);
        TCPServer server = new TCPServer(model);
        server.execute();
    }
}
