package com.assignment;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    // keeps track of animal count / index
    static int animalIndex = 0;
    // each species makes a sound
    static Map<String, String> sounds = new HashMap<>();
    static {
        sounds.put("dog", "bark!");
        sounds.put("cat", "meow!");
        sounds.put("sheep", "baa!");
    }

    public static void main(String[] args){
        // create & initialize scanner to receive user input
        Scanner sc = new Scanner(System.in);
        // set animals array to hold up to 100 Animal objects per assignment requirement
        final int CAPACITY = 100;
        Animal[] animals = new Animal[CAPACITY];
        //while loop to accept multiple animals at once
        boolean running = true;
        while(running){
            String response = sc.nextLine();
            // end loop if no response give by user
            if(response.equals("")){
                running = false;
            } else{
                String[] values = response.split(",");
                animals[animalIndex] = new Animal(values[0], values[1], values[2], values[3]);
                animalIndex++;
            }
        }

        // get the most common Animal
        String commonAnimal = mostCommonAnimal(animals);
        // Gets the relative sound from hashmap
        String commonSound = sounds.get(commonAnimal);
        // Gets the oldest animal from the group of most common animal
        int oldestIndex = oldestCommonAnimal(animals, commonAnimal);
        //Print resulting output
        System.out.println(animals[oldestIndex].name + ", the "  + animals[oldestIndex].color + " " + commonAnimal + " says " + commonSound);
    }


        //most common animal method. args accepted: animals array of Animal objects. gets the most common Animal species from the input

    public static String mostCommonAnimal(Animal[] animals){
        int dogCount = 0;
        int catCount = 0;
        int sheepCount = 0;
        // counter for each animal species type
        for(int i = 0; i < animalIndex; i++){
            if(animals[i].species.equals("dog")){
                dogCount++;
            } else if(animals[i].species.equals("cat")){
                catCount++;
            } else if(animals[i].species.equals("sheep")){
                sheepCount++;
            }
        }

        // find the max animal species from the counter
        int max = Math.max(dogCount, Math.max(catCount, sheepCount));
        if(max == dogCount){
            return "dog";
        } else if(max == catCount){
            return "cat";
        } else if(max == sheepCount){
            return "sheep";
        }
        return "";
    }

        //oldest common animal method. args accepted: animals array of Animal objects, most common animal string. gets oldest animal from the group of the most common animals
    public static int oldestCommonAnimal(Animal[] animals, String common){
        String[] start = animals[0].birthday.split("/");
        int oldestIndex = 0;
        int oldestDay = Integer.parseInt(start[0]);
        int oldestMonth = Integer.parseInt(start[1]);
        int oldestYear = Integer.parseInt(start[2]);
        // loop through to find oldest animal from the group
        for(int i = 1; i < animalIndex; i++){
            if(animals[i].species.equals(common)){
                String[] date = animals[i].birthday.split("/");
                int currentDay = Integer.parseInt(date[0]);
                int currentMonth = Integer.parseInt(date[1]);
                int currentYear = Integer.parseInt(date[2]);
                // check and finds the oldest date
                if(currentYear < oldestYear){
                    oldestYear = currentYear;
                    oldestMonth = currentMonth;
                    oldestDay = currentDay;
                    oldestIndex = i;
                    //if birth year is same check birth month
                } else if (currentYear == oldestYear){
                    if(currentMonth < oldestMonth){
                        oldestYear = currentYear;
                        oldestMonth = currentMonth;
                        oldestDay = currentDay;
                        oldestIndex = i;
                        //if birth month is same check birth day
                    } else if(currentMonth == oldestMonth){
                        if(currentDay < oldestDay){
                            oldestYear = currentYear;
                            oldestMonth = currentMonth;
                            oldestDay = currentDay;
                            oldestIndex = i;
                        }
                    }
                }
            }
        }
        return oldestIndex;
    }
}
