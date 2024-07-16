using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TableButton : MonoBehaviour
{
    public GameObject table ; 
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
    }

    public void OnClick(){
            table.SetActive(true);
    }
}
