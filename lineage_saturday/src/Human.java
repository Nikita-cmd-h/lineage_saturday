import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Human {
    private long id;
    private String name;
    private Gender gender;
    private LocalDate birthDate, deathDate;
    private List<Human> parents;
    private List<Human> children;
    private Human spouse;

    public Human(String name, Gender gender, LocalDate birthDate, LocalDate deathDate, Human father, Human mother) {
        id = -1;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        parents = new ArrayList<>();
        if(father != null){
            parents.add(father);
        }
        if(mother != null){
            parents.add(mother);
        }
        children = new ArrayList<>();

    }

    public Human(String name, Gender gender, LocalDate birthDate) {
        this(name, gender, birthDate, null, null, null);
    }
    public Human(String name, Gender gender, LocalDate birthDate, Human father, Human mother){
        this(name, gender, birthDate, null, father, mother);
    }

    public boolean addChild(Human child){
        if(!children.contains(child)){
            children.add(child);
            return true;
        }
        return false;
    }

    public boolean addParent(Human parent){
        if(!parents.contains(parent)){
            parents.add(parent);
            return true;
        }
        return false;
    }

    public Human getFather(){
        for(Human parent : parents){
            if(parent.getGender() == Gender.Male){
                return parent;
            }
        }
        return null;
    }

    public Human getMather(){
        for(Human parent : parents){
            if(parent.getGender() == Gender.Female){
                return parent;
            }
        }
        return null;
    }

    public int getAge(){
        if(deathDate == null){
            return getPeriod(birthDate, LocalDate.now());
        }else{
            return getPeriod(birthDate, deathDate);
        }
    }

    private int getPeriod(LocalDate birthDate, LocalDate deathDate){
        Period diff = Period.between(birthDate, deathDate);
        return diff.getYears();
    }

    public void setSpouse(Human spouse){this.spouse = spouse;}

    public Human getSpouse(){return spouse;}

    public String getName(){return name;}

    public long getId(){return id;}

    public void setId(long id){this.id = id;}

    public LocalDate getBirthDate(){return birthDate;}

    public List<Human> getParents(){return parents;}

    public List<Human> getChildren(){return children;}

    public void setBirthDate(LocalDate birthDate){this.birthDate = birthDate;}

    public void setDeathDate(LocalDate deathDate){this.deathDate = deathDate;}

    public Gender getGender(){return gender;}

    @Override
    public String toString(){
        return getInfo();
    }

    public String getInfo() {
        StringBuilder s = new StringBuilder();
        s.append("Id: "+id+";\n");
        s.append("Имя: "+name+";\n");
        s.append("Пол: "+getGender()+";\n");
        s.append("Возраст: "+getAge()+";\n");
        s.append(": "+getSpouse()+";\n");
        s.append("Мать: "+getMather()+";\n");
        s.append("Отец: "+getFather()+";\n");
        s.append("Дети: "+getChildren()+";\n");
        return s.toString();
    }

    public String getSpouseInfo(){
        String res = "супруг(а): ";
        if(spouse == null){
            res+="нет";
        }else{
            res+=spouse.getName();
        }
        return res;
    }

    public String getMatherInfo(){
        String res = "Мать: ";
        Human mather = getMather();
        if(mather == null){
            res+="неизвестна";
        }else{
            res+=mather.getName();
        }
        return res;
    }

    public String getFatherInfo(){
        String res = "Отец: ";
        Human father = getFather();
        if(father == null){
            res+="неизвестен";
        }else{
            res+=father.getName();
        }
        return res;
    }

    public String getChildrenInfo(){
        StringBuilder res = new StringBuilder();
        res.append("Дети:");
        if(children.size()!=0){
            res.append(children.get(0).getName());
            for(int i = 1; i < children.size(); i++){
                res.append(", "+ children.get(i).getName());
            }
        }else {
            res.append("отсуствуют");
        }

        return res.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(!(obj instanceof Human)){
            return false;
        }
        Human human = (Human) obj;
        return human.getId() == getId();
    }
}
