/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

/**
 * Toplantı aktivitesi
 * 
 * @author Hakan Uygun
 */
public class MeetingActivity extends Activity{
    
    /**
     * Toplantı nerede?
     */
    private String location;
    
    /**
     * Toplantı kimlerle
     */
    private String attendees;
    
    /**
     * Toplantı notları
     */
    private String meetingMinutes;
}
