package uy.edu.um.entities;

public class CrewMember {
    String creditId;
    String department;
    int gender;
    int id;
    String job;
    String name;
    String profilePath;

    public CrewMember(String crewString) {
        String[] result = crewString.split(", 'profile_path': ");
        String profilePath = result[1];

        result = result[0].split(", 'name': ");
        String name = result[1];

        result = result[0].split(", 'job': ");
        String job = result[1];

        result = result[0].split(", 'id': ");
        int id = Integer.parseInt(result[1]);

        result = result[0].split(", 'gender': ");
        int gender = Integer.parseInt(result[1]);

        result = result[0].split(", 'department': ");
        String department = result[1];

        result = result[0].split("'credit_id': ");

        this.creditId = result[1];
        this.department = department;
        this.gender = gender;
        this.id = id;
        this.job = job;
        this.name = name;
        this.profilePath = profilePath;
    }
}