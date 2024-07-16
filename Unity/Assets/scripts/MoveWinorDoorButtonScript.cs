using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class MoveWinorDoorButtonScript : MonoBehaviour
{
        [SerializeField] private  GameObject[] DoorsFills ;
        [SerializeField] private  GameObject[] WinFills ;
        [SerializeField] private  GameObject[] Wins ;
        [SerializeField] private  GameObject[] Doors ;
        private static int doorcounter = 7 ;
        private static int wincounter = 0 ; 

    public void OnClick(){
        string ClickedButton = EventSystem.current.currentSelectedGameObject.name;
        if(RoomScript.Current == "glass" || RoomScript.Current == "glass (1)"){
            //disable active
            Wins[wincounter].SetActive(false);
            WinFills[wincounter].SetActive(true);
            if(ClickedButton == "Right"){
                wincounter ++;
                if(wincounter>3){
                    wincounter = 0;
                }
            }
            else if(ClickedButton == "Left"){
                wincounter --;
                if(wincounter < 0 ){
                    wincounter = 3;
                }
            }
                        Debug.Log(wincounter);
            Wins[wincounter].SetActive(true);
            WinFills[wincounter].SetActive(false);
            //enable active
        }
        else{
            //disable active
            Doors[doorcounter].SetActive(false);
            DoorsFills[doorcounter].SetActive(true);
            if(ClickedButton == "Right"){
                doorcounter ++;
                if(doorcounter>7){
                    doorcounter = 0;
                }
            }
            else if(ClickedButton == "Left"){
                doorcounter --;
                if(doorcounter < 0 ){
                    doorcounter = 7;
                }
            }
            Debug.Log(doorcounter);
            Doors[doorcounter].SetActive(true);
            DoorsFills[doorcounter].SetActive(false);
            //enable active 
        }
    }
}
