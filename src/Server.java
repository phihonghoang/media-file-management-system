import domainLogic.MediaUploadableAdmin;
import net.TCPServer;

public class Server {
    public static void main(String[] args) {
        MediaUploadableAdmin model = new MediaUploadableAdmin(1000);
        TCPServer server = new TCPServer(model);
        server.execute();
    }
}
