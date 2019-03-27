package szp.rafael.ss;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.sun.org.apache.bcel.internal.classfile.SourceFile;

import java.util.Arrays;

public class SerialReader {

  public static void main(String... args){

    final SerialPort comPort = SerialPort.getCommPorts()[0];
    comPort.openPort();
    comPort.addDataListener(new SerialPortDataListener() {
      @Override
      public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
      @Override
      public void serialEvent(SerialPortEvent event)
      {
        if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
          return;
        byte[] newData = new byte[comPort.bytesAvailable()];
        int numRead = comPort.readBytes(newData, newData.length);

        System.out.printf("Read " + numRead + " bytes."+ "With data "+ Arrays.toString(newData));
        System.out.printf("\tWith event type %s \n",event.getEventType());
      }
    });

//    comPort.addDataListener(new SerialPortDataListener() {
//      @Override
//      public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }
//
//      @Override
//      public void serialEvent(SerialPortEvent event)
//      {
//        byte[] newData = event.getReceivedData();
//        System.out.printf("Received data of size: " + newData.length);
//        System.out.printf(" With data: \n %s",Arrays.toString(newData));
//
////        for (int i = 0; i < newData.length; ++i)
////          System.out.print((char)newData[i]);
//        System.out.println("\n");
//
//      }
//    });
  }

}
