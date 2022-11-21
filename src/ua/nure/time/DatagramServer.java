package  ua.nure.time;
import java.net.*;
import java.util.*;

public class DatagramServer
{
    public static void main(String args[])
    {
        byte bKbdInput[] = new byte[256];
        // Буфер для чтения команд
        byte buf[] = new byte[512];
        // Сокет сервера
        DatagramSocket s;
        // Рабочая строка
        String str;
        // Передаваемый пакет
        DatagramPacket pout;
        // Адрес узла, откуда пришел принятый пакет
        InetAddress SrcAddress;
        // Порт, откуда пришел принятый пакет
        int SrcPort;
        // Принимаемый пакет
        DatagramPacket pinp;

        try
        {
            // Выводим строку приглашения
            System.out.println("Datagramm Socket Server Application");
        }
        catch(Exception ioe)
        {
            // При возникновении исключения выводим его описание
            // на консоль
            System.out.println(ioe.toString());
        }

        try
        {
            // Создаем сокет сервера
            s = new DatagramSocket(1500);
            // Создаем пакет для приема команд
            pinp = new DatagramPacket(buf, 512);
            // Принимаем пакет от клиента
            s.receive(pinp);
            // Получаем адрес узла, приславшего пакет
            SrcAddress = pinp.getAddress();
            // Получаем порт, на котором был передан пакет
            SrcPort    = pinp.getPort();

/////////////////////////////////////////

            DateMessage  dateMessage  =  new  DateMessage(
                    Calendar.getInstance().getTime(),
                    "The current date / time on the server");

            String mess = dateMessage.getDate().toString() + " The current date / time on the server" ;

            bKbdInput = mess.getBytes("US-ASCII");

            ///////////////////////////////////////////////////////////////////////////////////
            pout = new DatagramPacket(bKbdInput, bKbdInput.length,
                    SrcAddress, SrcPort);

            // Посылаем пакет клиенту
            s.send(pout);

            // Закрываем сокет сервера
            s.close();

        }
        catch(Exception ioe)
        {
            System.out.println(ioe.toString());
        }

    }
}
