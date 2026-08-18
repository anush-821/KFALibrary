package kfa.model;

// Interface for items that can be extended beyond original issue time
public interface Renewable {
    void renew(int extraDays);
}