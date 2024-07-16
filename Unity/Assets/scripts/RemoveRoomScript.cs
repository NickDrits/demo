using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RemoveRoomScript : MonoBehaviour
{

    [SerializeField] private  GameObject Selector ;
    [SerializeField] private  GameObject Buttons ;

    public void OnClick(){
        Selector.SetActive(false) ;
        Buttons.SetActive(true) ;
    }
}
