package szp.rafael.ss;

import com.fazecast.jSerialComm.SerialPort;

public class SerialPacketReader {

  static public void main(String[] args)
  {
    if(SerialPort.getCommPorts().length==0) {
      System.err.println("OPA! Não há portas COM abertas");
      return;
    }
    SerialPort comPort = SerialPort.getCommPorts()[0];
    comPort.setBaudRate(19200);
    comPort.setParity(SerialPort.NO_PARITY);
    comPort.setNumDataBits(8);
    comPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
    comPort.openPort();
    PacketListener listener = new PacketListener();
    comPort.addDataListener(listener);
    while(true){
    try { Thread.sleep(5000); } catch (Exception e) { e.printStackTrace(); }
    }
//    comPort.removeDataListener();
//    comPort.closePort();
  }

}
