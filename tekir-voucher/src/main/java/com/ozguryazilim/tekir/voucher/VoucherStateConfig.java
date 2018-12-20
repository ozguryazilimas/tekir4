package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.entities.VoucherStateType;
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
    private final Map<VoucherStateType,List<VoucherStateAction>> stateTypeActions = new HashMap<>();
    
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
    
    /**
     * StateType'e bağlı olarak UI üzerinde ek actionlar sağlar.
     * 
     * Örneğin OPEN stateType'ında olan tüm stateler için
     * 
     * @param stateType
     * @param action 
     */
    public void addStateTypeAction( VoucherStateType stateType, VoucherStateAction action){
        List<VoucherStateAction> acts = stateTypeActions.get(stateType);
        if( acts == null ){
            acts = new ArrayList<>();
            stateTypeActions.put(stateType, acts);
        }
        
        acts.add(action);
    }
    
    public List<VoucherState> getStates() {
        return states;
    }

    public Map<String, VoucherStateAction> getActions() {
        return actions;
    }

    /**
     * Geriye verilen state için tanımlı tüm action'ları döner.
     * 
     * Listeye verilen State'in type'ı için tanımlı olanlarda döner.
     * 
     * @param state
     * @return 
     */
    public List<VoucherStateAction> getStateActions( VoucherState state ) {
        List<VoucherStateAction> result = new ArrayList<>();
        
        List<VoucherStateAction> ls = stateActions.get(state);
        if( ls != null ){
            result.addAll(ls);
        }
        
        ls = stateTypeActions.get(state.getType());
        if( ls != null ){
            result.addAll(ls);
        }
        
        return result;
    }
    
    public Map<VoucherState, Map<VoucherStateAction, VoucherState>> getTransitions() {
        return transitions;
    }

    
}
