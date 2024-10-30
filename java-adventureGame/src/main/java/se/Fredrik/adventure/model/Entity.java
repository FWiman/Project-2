package se.Fredrik.adventure.model;

public abstract class Entity {
    int health;
    int damage;
    String role;


    public Entity(int health, int damage, String name) {
        this.health = health;
        this.damage = damage;
        this.role = name;
    }

    public String getRole(){
        return role;
    }

    public int getDamage(){
        return damage;
    }

    public int getHealth(){
        return health;
    }

    public int setDamage(int damage){
        return this.damage = damage;
    }

    public void takeHit(int damage) {
        health -= damage;
    }

    public void punch(Entity toPunch){
        toPunch.takeHit(this.damage);
    }

    public boolean isConscious(){
        return this.health > 0;
    }

}
