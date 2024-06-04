using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class camerascript : MonoBehaviour
{
    public float rotationSpeed = 0.01f;
    private Touch touch;
    public static bool allow = true;

    void Update()
    {
        if (Input.touchCount == 1 && allow)
        {
            touch = Input.GetTouch(0);
            if (touch.phase == TouchPhase.Moved)
            {
                // Rotate the camera around the Y axis based on the touch delta position
                transform.Rotate(0, touch.deltaPosition.x * rotationSpeed, 0);
            }
        }
    }
}
