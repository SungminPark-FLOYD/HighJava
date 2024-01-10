package Network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        //InetAddress 클래스 ==> IP주소를 다루기 위한 클래스

        //www.naver.com의 IP정보 가져오기
        InetAddress ip = InetAddress.getByName("www.naver.com");

        System.out.println("Host Name : " + ip.getHostName());
        System.out.println("Host Address : " + ip.getHostAddress());
        System.out.println("toString : " + ip.toString());
        System.out.println();

        //자신의 컴퓨터의 IP정보 가져오기
         InetAddress localIp = InetAddress.getLocalHost();
         System.out.println("Host Name : " + localIp.getHostName());
         System.out.println("Host Address : " + localIp.getHostAddress());
         System.out.println("toString : " + localIp.toString());
         System.out.println();

         //ip주소가 여러개인 호스트의 IP정보 가져오기
        InetAddress[] ipAddr = InetAddress.getAllByName("www.google.com");
        for(InetAddress ad : ipAddr){
            System.out.println("Host Name : " + ad.getHostName());
            System.out.println("Host Address : " + ad.getHostAddress());
            System.out.println("toString : " + ad.toString());
        }
        System.out.println();
    }
}
