package com.cs4013.Admin;

import com.cs4013.Misc.FileManager;
import com.cs4013.Misc.StringUtils;
import com.cs4013.Misc.TerminalColor;
import com.cs4013.Misc.TerminalLogger;
import com.cs4013.Model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HotelManager {
    public int width = 48;
    FileManager fileManager = new FileManager("hotels.csv");

    //Ratings
//name
    public boolean addHotel() throws IOException {

        boolean continueGoing = false;
        TerminalLogger.logln("+".repeat(width));
        TerminalLogger.logln("Create Hotel");
        TerminalLogger.logln("+".repeat(width) + "\n");
        String input = "";
        String rating = "";
        String name = "";

        boolean keepGoing = false;

        boolean success = false;
        success = false;
        Rates rates = new Rates();

        while (success == false) {
            input = TerminalLogger.textfield("Enter Hotel Name", width);
            if (input.matches("[0-9A-Za-z\\s]+")) {
                name = input;
                success = true;
            } else {
                TerminalLogger.logError("Please enter Alpha-numeric or numeric characters!");
            }
        }

        while (success == false) {
            input = TerminalLogger.textfield("Enter Rating", width);
            if (input.matches("[0-9A-Za-z\\s]+")) {
                rating = input;
                success = true;
            } else {
                TerminalLogger.logError("Please enter Alpha-numeric or numeric characters!");
            }
        }
        Hotel hotel = new Hotel(rating, name);
        fileManager.write(hotel.toString());
        TerminalLogger.logln("✓".repeat(width),TerminalColor.ANSI_GREEN);
        TerminalLogger.logln("Hotel Successfully Added", TerminalColor.ANSI_GREEN);
        TerminalLogger.logln("✓".repeat(width) + "\n",TerminalColor.ANSI_GREEN);

        String s = TerminalLogger.textfield("Would you like to add another Hotel? y/n",width);
        if(s.equals("y")){
            keepGoing = true;
        }
        return keepGoing;
    }


//ask user  -for hotel name(string(word + num. only)),
//          -hotel rating(combination of a word + number)
//          save into csv file (hotel.csv)
//          prints - want to cont.


//
//    public void editHotel() {
//    }
//
//    public void deleteHotel() {
//    }
//
//    public void viewHotel() {

    public void editHotel() {
        TerminalLogger.logln("+".repeat(width));
        TerminalLogger.logln(StringUtils.centerString("Admin Control Centre | Edit Hotel", 48, "|"));
        TerminalLogger.logln("+".repeat(width) + "\n");
    }

    public void deleteHotel() {
        TerminalLogger.logln("+".repeat(width));
        TerminalLogger.logln(StringUtils.centerString("Admin Control Centre | Delete Hotel", 48, "|"));
        TerminalLogger.logln("+".repeat(width) + "\n");
    }

    public void viewHotel() {
        TerminalLogger.logln("+".repeat(width));
        TerminalLogger.logln(StringUtils.centerString("Admin Control Centre | View Hotel", 48, "|"));
        TerminalLogger.logln("+".repeat(width) + "\n");
    }
}