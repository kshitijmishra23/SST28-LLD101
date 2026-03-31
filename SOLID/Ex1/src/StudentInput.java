// DTO
// Well , we want the parser to give us only the structutred data only and we don't want to create the studentRecord  yet.


public class StudentInput {
    String name;
    String email;
    String phone;
    String program;

    StudentInput(String name , String email, String phone , String program){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.program = program;

    }
}
