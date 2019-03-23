/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enetjava.objects;

import lombok.Getter;
import lombok.Setter;

public class MutableInteger extends Number {

    @Getter @Setter
    private int value;
    
    public MutableInteger(int value)
    {
        this.value = value;
    }

    @Override
    public double doubleValue() {
        return (double) this.value;
    }

    @Override
    public float floatValue() {
        return (float) this.value;
    }

    @Override
    public int intValue() {
        return this.value;
    }

    @Override
    public long longValue() {
        return this.value;
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
    
    @Override
    public int hashCode() {
        return value;
    }
    
    @Override
    public boolean equals(Object obj) {
        try {
            return ((MutableInteger) obj).value == this.value;
        } catch (NullPointerException | ClassCastException npe) {
            return false;
        }
    }
}
