package ua.nure.time;
import java.net.*;

public class DatagramClient
{

    public static void main(String args[])
    {
        // Массив для строки
        byte bKbdInput[] = new byte[512];
        // Размер введенной строки
        int length;
        // Рабочая строка
        String str;
        // Передаваемый пакет
        DatagramPacket pout;
        // Сокет клиента
        DatagramSocket s;
        // Принимаемый пакет
        DatagramPacket pinp;

        try
        {
            // Выводим строку приглашения
            System.out.println("Datagram Socket Client Application");

        }
        catch(Exception ioe)
        {
            // При возникновении исключения выводим его описание
            // на консоль
            System.out.println(ioe.toString());
        }

        try
        {   // Получаем адрес локального узла
            InetAddress OutAddress = InetAddress.getLocalHost();

            // Создаем сокет с использованием любого
            // свободного порта
            s = new DatagramSocket();

            // Создаем передаваемый пакет инициируюющий соеденение
            pout = new DatagramPacket(bKbdInput, bKbdInput.length,
                    OutAddress, 1500);
            s.send(pout);
            //s.connect(OutAddress, 1500);

//////////////////////////////////////////
            // Создаем пакет для приема команд
            pinp = new DatagramPacket(bKbdInput, 512);

            // Принимаем пакет от сервера
            s.receive(pinp);


            // Формируем строку из принятого блока
            str = new String(bKbdInput, 0);


            // Выводим строку команды на консоль
            System.out.println( str );
/////////////////////////////////////////////////////////


            // Закрываем сокет
            s.close();
        }
        catch(Exception ioe)
        {
            System.out.println(ioe.toString());
        }

    }
}
