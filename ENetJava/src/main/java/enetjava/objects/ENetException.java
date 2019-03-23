/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enetjava.objects;

public class ENetException extends Exception {
    public ENetException() { super(); }
    public ENetException(String message) { super(message); }
    public ENetException(Throwable cause) { super(cause); }
    public ENetException(String message, Throwable cause) { super(message, cause); }
}