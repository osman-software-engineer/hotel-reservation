package com.hotelreservation.model;

import java.util.Objects;

public class Room implements IRoom{

    String roomNumber;
    Double price;
    RoomType roomType;
    String status;

    public Room(String roomNumber, Double price, RoomType roomType, String status) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.status = status;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Double getRoomPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", enumeration=" + roomType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals(getRoomNumber(), room.getRoomNumber()) &&
                Objects.equals(price, room.price) &&
                getRoomType() == room.getRoomType() &&
                Objects.equals(getStatus(), room.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber(), price, getRoomType(), getStatus());
    }
}
