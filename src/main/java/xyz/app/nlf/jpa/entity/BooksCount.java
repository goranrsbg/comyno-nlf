/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xyz.app.nlf.jpa.entity;

/**
 *
 * @author Lap
 */
public class BooksCount {

    private int qty;
    private int qtyLoan;

    public BooksCount() {
    }

    public BooksCount(int qty, int qtyLoan) {
        this.qty = qty;
        this.qtyLoan = qtyLoan;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getQtyLoan() {
        return qtyLoan;
    }

    public void setQtyLoan(int qtyLoan) {
        this.qtyLoan = qtyLoan;
    }

    @Override
    public String toString() {
        return "BooksCount{" + "qty=" + qty + ", qtyLoan=" + qtyLoan + '}';
    }
    
}
