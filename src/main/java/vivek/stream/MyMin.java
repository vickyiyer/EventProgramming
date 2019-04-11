package vivek.stream;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyMin {

    public static void main(String[] args) {

        int [] numbers={1,4,5,6,1,10, 11, 100, 2};

     IntStream.of(numbers).min().ifPresent(System.out::println);

     //Get me the three smallest number in an Array

        IntStream.of(numbers).distinct().sorted().limit(3).forEach(System.out::println);

        List<String> collect = getEmployeeList().stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .map(Employee::getFirstName)
                .collect(Collectors.toList());

        getEmployeeList().stream().sorted(Comparator.comparing(Employee::getBirthDate)).
                forEach(e-> System.out.println("Employee Name: "+ e.getFirstName()+ " Birthdate " + e.getBirthDate().toString()));


    }


    public static List<Employee> getEmployeeList(){

        Employee employee1=new Employee.Builder("Vivek" ,"Ramaswamy", LocalDate.of(1973,9, 15))
                .setSalary(1234.67)
                .setActiveFlag(true)
                .build();
        Employee employee2=new Employee.Builder("Vinayak" ,"Ramaswamy", LocalDate.of(1975,12, 6))
                .setSalary(3000.8978)
                .setActiveFlag(true)
                .build();
        Employee employee3=new Employee.Builder("Arjun" ,"Ramaswamy", LocalDate.of(2003,12, 15))
                .setSalary(4000.90)
                .setActiveFlag(true)
                .build();
        Employee employee4=new Employee.Builder("Ajay" ,"Ramaswamy", LocalDate.of(2008,10, 17))
                .setSalary(5000.78)
                .setActiveFlag(true)
                .build();
        Employee employee5=new Employee.Builder("Anirudh" ,"Ramaswamy", LocalDate.of(2008,12, 4))
                .setSalary(9500.56)
                .setActiveFlag(true)
                .build();

        return List.of(employee1,employee2,employee3, employee4, employee5);

    }
}
