/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package xyz.app.nlf.utils;

import xyz.app.nlf.jpa.entity.Book;
import xyz.app.nlf.jpa.entity.Student;

/**
 * Enable controllers to receive book and student entity.
 * 
 * @author Lap
 */
public interface Settable {
    public void setBook(Book book);
    public void setStudent(Student student);
}
