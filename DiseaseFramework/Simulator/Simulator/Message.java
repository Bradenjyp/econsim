package Simulator;

class Message extends Event {
  MessageHandler messageHandler;
  String command;
  AbstractSimulator simulator;
  Message(MessageHandler messageHandler, String command) {
    this.messageHandler = messageHandler;
    this.command = command;
  }
  Message(MessageHandler messageHandler) {
    this.messageHandler = messageHandler;
    this.command = null;
  }
  void set(String command, double time) {
    this.command = command;
    this.time = time;
  }
  void execute(AbstractSimulator simulator) {
    this.simulator = simulator;
    if (command != null && messageHandler != null) {
      messageHandler.handle(this);
    }
  }
}