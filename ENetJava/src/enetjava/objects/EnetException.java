/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enetjava.objects;

public class EnetException extends Exception
{
    private static final long serialVersionUID = -5753326622210863553L;
    public EnetException() { super(); }
    public EnetException(String message) { super(message); }
    public EnetException(Throwable cause) { super(cause); }
    public EnetException(String message, Throwable cause) { super(message, cause); }
}