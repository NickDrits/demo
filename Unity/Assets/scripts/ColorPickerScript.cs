using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;

public class ColorPickerScript : MonoBehaviour
{
    [SerializeField] private Material material;
    [SerializeField] private  GameObject Buttons ;
    [SerializeField] private  GameObject Picker ;
    [SerializeField] private  GameObject Colorb ;


    private string[] Colors = { "808080" , "FFFFFF" , "D2B48C" , "826B48" , "362624" , "C2B280"};

    public void OnClickPickerColor(){

        string clickedButtonName = EventSystem.current.currentSelectedGameObject.name;

        int buttonIndex;
        if (int.TryParse(clickedButtonName, out buttonIndex) && buttonIndex >= 0 && buttonIndex < Colors.Length)
        {
            Debug.Log(buttonIndex);
            string hexColor = Colors[buttonIndex];

            Color color;
            if (ColorUtility.TryParseHtmlString("#" + hexColor, out color))
            {
                material.color = color;
            }
            else
            {
                Debug.LogError("Invalid color code: " + hexColor);
            }
        }
        else
        {
            Debug.LogError("Invalid button index: " + clickedButtonName);
        }
        Buttons.SetActive(true) ;
        Picker.SetActive(false) ;
        Colorb.SetActive(true);
        
    }
}
