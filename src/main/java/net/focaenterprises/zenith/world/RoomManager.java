package net.focaenterprises.zenith.world;

import java.util.ArrayList;
import java.util.List;

public class RoomManager {
  private final List<Room> rooms;
  public int currentRoomIndex;

  public RoomManager() {
    this.rooms = new ArrayList<>();
  }

  public List<Room> getRooms() {
    return rooms;
  }

  public Room getCurrentRoom() {
    return rooms.get(currentRoomIndex);
  }

  public boolean nextRoom() {
    if (currentRoomIndex != rooms.size() - 1) {
      currentRoomIndex++;

      return true;
    } else {
      return false;
    }
  }
}
