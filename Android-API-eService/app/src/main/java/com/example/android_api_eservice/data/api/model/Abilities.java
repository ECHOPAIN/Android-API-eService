package com.example.android_api_eservice.data.api.model;

public class Abilities {
    private boolean is_hidden;
    private String slot;
    private Ability ability;

    public boolean isIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(boolean is_hidden) {
        this.is_hidden = is_hidden;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public Ability getAbility() { return ability; }

    public void setAbility(Ability ability) { this.ability = ability; }
}
