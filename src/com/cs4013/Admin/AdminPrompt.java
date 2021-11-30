package com.cs4013.Admin;

import com.cs4013.Interface.IPrompt;
import com.cs4013.Misc.StringUtils;
import com.cs4013.Misc.TerminalColor;
import com.cs4013.Misc.TerminalLogger;
import com.cs4013.Model.Hotel;

import java.io.IOException;
import java.util.*;

public class AdminPrompt implements IPrompt {

    public boolean keepGoing = true;
    String currentPath = "/";
    public Map<String,String> definition =new HashMap<>();
    public Map<String,ArrayList<String>> navStack = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    ArrayList<String> prevPath = new ArrayList<String>();
    int width = 50;
    private RoomManager roomManager = new RoomManager();
    private HotelManager hotelManager = new HotelManager();

    public AdminPrompt(){
        populateNavStack();
    }
    private void populateNavStack(){

        definition.put("MR","Enter MR to Modifify Rooms");
        definition.put("MH", "Enter MH to Modify Hotels"); 
        definition.put("AH","Enter AH for data analysis for hotel" );

        definition.put("AR","Enter AR to Add Rooms");
        definition.put("ER","Enter ER to Edit Rooms");
        definition.put("DR","Enter DR to Delete rooms");
        definition.put("VR","Enter VR to View Rooms");

        

        ArrayList<String> init = new ArrayList<>();
        init.add("MR");
        init.add("MH");
        init.add("AH");
        navStack.put("/",init);

         init = new ArrayList<>();
         init.add("AR");
         init.add("ER");
         init.add("DR");
         init.add("VR");
         navStack.put("MR",init);
         

         init = new ArrayList<>();


         navStack.put("MH",init);

         init = new ArrayList<>();

         navStack.put("AH",init);




         

         
    }
    public void printDefiniton(String command){
        for(String s : navStack.get(command)){
           TerminalLogger.logln(definition.get(s), TerminalColor.ANSI_CYAN);
        }
            
            
    }
    public boolean addRoom()throws IOException{
        return  roomManager.addRoom();
    }
    public boolean addHotel()throws IOException{
        return hotelManager.addHotel();
    }
    public void editRoom(){

    }
    public void deleteRoom(){

    }
    public void viewRoom(){

    }  
    public void goBack(){
        if(prevPath.size() > 0){
            currentPath = prevPath.remove(prevPath.size()-1);
        }
        else{
            keepGoing = false;
        }
    }

    @Override
    public void  display(String command){
        switch(command){
            case "AR": 
            try{
               if(!addRoom()){
                    goBack();
               }
               
            }catch(IOException e){

            }
            break;
            case "AH":
                try{
                    if(!addHotel()){
                        goBack();
                    }

                }catch(IOException e){

                }
                break;
            case "EH":
            editRoom();
            case "VH":
            viewRoom();
            default:
            printDefiniton(command);
            String input = TerminalLogger.textfield("Enter Here", width);
            if(input.equals("back") || (input.equals("exit"))){
                if(input.equals("exit")){
                    keepGoing = false;
                }
                else{
                    goBack();
                }
               
            }
            else{
                if(navStack.get(currentPath).contains(input)){

                    prevPath.add(currentPath);
                    currentPath = input;
    
                }
                else{
                    TerminalLogger.logError("Command " + input + " not recognized! ");
                }
            }
            
            break;
        }
     }
        
        
    public void execute(){

        TerminalLogger.logln("=".repeat(width));
        TerminalLogger.logln(StringUtils.centerString("Admin Control Center", 50, "|"));
        TerminalLogger.logln("=".repeat(width)+ "\n");
        while(keepGoing){
            display(currentPath);
            if(!keepGoing){
                String result = TerminalLogger.textfield("ARe you sure you want to exit? y/n", width);
                if(result.equals("n")){
                    keepGoing = true;
                }

                }
            }
        }

    }
    
    
