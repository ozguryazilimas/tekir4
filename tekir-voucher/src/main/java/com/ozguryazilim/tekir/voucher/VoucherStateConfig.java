/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.entities.VoucherState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Voucher State Machine için config modeli.
 * 
 * Voucher için kullanılacka olan state ve geçiş tanımları.
 * 
 * @author Hakan Uygun
 */
public class VoucherStateConfig {

    private final List<VoucherState> states = new ArrayList<>();
    private final Map<String,VoucherStateAction> actions = new HashMap<>();
    private final Map<VoucherState, Map<VoucherStateAction,VoucherState>> transitions = new HashMap<>();
    private final Map<VoucherState,List<VoucherStateAction>> stateActions = new HashMap<>();
    
    /**
     * Hangi durumdan hangi action ile hangi duruma geçilecek.
     * 
     * @param from
     * @param action
     * @param to 
     */
    public void addTranstion( VoucherState from, VoucherStateAction action, VoucherState to ){
       if( !states.contains(from) ){ 
           states.add(from);
       }
       
       if( !states.contains(to) ){ 
           states.add(to);
       }
       
       if( !actions.containsKey(action.getName())){
           action.setOrder(actions.size());
           actions.put(action.getName(), action);
       }
       
       Map<VoucherStateAction,VoucherState> trn = transitions.get(from);
       if( trn == null ){
           trn = new HashMap<>();
           transitions.put(from, trn);
       }
       
       //TODO: Acaba daha önce var olan bir action için itiraz etmeli mi?
       trn.put(action, to);
    }

    /**
     * State'e bağlı olarak UI üzerinde ek actionlar sağlar.
     * @param state
     * @param action 
     */
    public void addStateAction( VoucherState state, VoucherStateAction action){
        List<VoucherStateAction> acts = stateActions.get(state);
        if( acts == null ){
            acts = new ArrayList<>();
            stateActions.put(state, acts);
        }
        
        acts.add(action);
    }
    
    public List<VoucherState> getStates() {
        return states;
    }

    public Map<String, VoucherStateAction> getActions() {
        return actions;
    }

    public List<VoucherStateAction> getStateActions( VoucherState state ) {
        return stateActions.get(state);
    }
    
    public Map<VoucherState, Map<VoucherStateAction, VoucherState>> getTransitions() {
        return transitions;
    }

    
}
