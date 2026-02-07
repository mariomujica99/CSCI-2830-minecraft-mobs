package com.mobs;

public class Mob {
    private final String name;
    private final String imageUrl;
    private final String behavior;

    private final String dimension;
    private final String spawn;

    private final Integer health;
    private final String healthHearts;

    private final Integer damage;
    private final String damageHearts;

    private final Double size;
    private final String sizeDescription;

    private final String description;

    public Mob(String name, String imageUrl, String behavior, 
                String dimension, String spawn, 
                Integer health, String healthHearts, 
                Integer damage, String damageHearts, 
                Double size, String sizeDescription, 
                String description) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.behavior = behavior;

        this.dimension = dimension;
        this.spawn = spawn;

        this.health = health;
        this.healthHearts = healthHearts;

        this.damage = damage;
        this.damageHearts = damageHearts;

        this.size = size;
        this.sizeDescription = sizeDescription;

        this.description = description;
    }

    public String getName() { return name; }
    public String getImageUrl() { return imageUrl; }
    public String getBehavior() { return behavior; }

    public String getDimension() { return dimension; }
    public String getSpawn() { return spawn; }

    public Integer getHealth() { return health; }
    public String getHealthHearts() { return healthHearts; }

    public Integer getDamage() { return damage; }
    public String getDamageHearts() { return damageHearts; }

    public Double getSize() { return size; }
    public String getSizeDescription() { return sizeDescription; }

    public String getDescription() { return description; }
}