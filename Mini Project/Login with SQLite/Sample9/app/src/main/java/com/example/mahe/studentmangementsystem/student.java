package com.example.mahe.studentmangementsystem;

public class student {
  int roll;
  String name;
  int marks;
  int age;
  String gender;
  int class1;

  public student(String name,int roll,int marks, int age, String gender, int class1){
    this.roll=roll;
    this.name=name;
    this.age=age;
    this.gender=gender;
    this.class1=class1;
  }

    //getting Roll
    public int getRoll() {
        return roll;
    }
    //setting Roll
    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getName(){
      return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public int getMarks(){
        return marks;
    }
    public void setMarks(){
        this.marks=marks;
    }

    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender=gender;
    }

    public int getclass(){
        return class1;
    }
    public void setClass(int class1){
        this.class1=class1;
    }
}
