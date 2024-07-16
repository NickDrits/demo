using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro ;

public class RoomScript : MonoBehaviour
{

    [SerializeField] private  GameObject Selector ;
    [SerializeField] private  GameObject Buttons ;
    [SerializeField] private  TextMeshProUGUI Text ;
    public static string Current;


    private Touch touch;

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {


        if (Input.touchCount > 0)
        {

            touch = Input.GetTouch(0);
            Ray camRay = Camera.main.ScreenPointToRay(touch.position);
            RaycastHit raycastHit;

            if (touch.phase == TouchPhase.Began)
            {
                if (Physics.Raycast(camRay, out raycastHit))
                {
                    if (raycastHit.transform == transform)
                    {
                        Selector.SetActive(true) ;
                        Buttons.SetActive(false) ;
                        Current = gameObject.name;
                        if(Current == "glass" || Current == "glass (1)"){
                            Text.text = "Window" ;                            
                        }
                        else {
                            Text.text = "Door" ;                            
                        }
                    }
                }
            }
        }
    }
}
