package vivek.stream;

import java.time.LocalDate;

public class Employee {

    private String firstName;
    private String lastName;

    private LocalDate birthDate;

    private boolean isActive;

    private double salary;

    private Employee(Builder builder){

        this.firstName=builder.firstName;
        this.lastName=builder.lastName;
        this.birthDate=builder.birthDate;
        this.salary=builder.salary;
        this.isActive=builder.isActive;


    }


    public String getFirstName() {
        return firstName;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public double getSalary() {
        return salary;
    }

    public static class Builder{

        //Required parameter
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private boolean isActive;

        //optional parameter

        private double salary=0;


        public Builder(String firstName, String lastName, LocalDate birthDate){
            this.firstName=firstName;
            this.lastName=lastName;
            this.birthDate=birthDate;
        }

        public Builder setSalary(double salary){

            this.salary=salary;

            return this;
        }

        public Builder setActiveFlag(boolean activeFlag){
            this.isActive=activeFlag;

            return this;
        }


        public Employee build (){

            return new Employee(this);
        }

    }









}
