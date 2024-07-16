using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ColorScreenScript : MonoBehaviour
{
    [SerializeField] private  GameObject Buttons ;
    [SerializeField] private  GameObject Picker ;
    [SerializeField] private  GameObject Colorb ;

    public void OnClick(){
        Buttons.SetActive(false) ;
        Picker.SetActive(true) ;
        Colorb.SetActive(false);
    }
}
