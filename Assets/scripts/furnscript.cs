using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class furnscript : MonoBehaviour
{
    public float moveSpeed = 0.01f;
    public float rotationSpeed = 0.01f;
    private Touch touch;
    private GameObject child;
    private bool isTouchingObject = false;

    void Start()
    {
        child = transform.GetChild(0).gameObject;
    }

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
                        isTouchingObject = true;
                        camerascript.allow = false; // Assuming camerascript is another script controlling camera movement
                        child.SetActive(true);
                    }
                }
            }

            if (isTouchingObject)
            {
                if (Input.touchCount == 1)
                {
                    if (touch.phase == TouchPhase.Moved)
                    {
                        Vector3 cameraForward = Camera.main.transform.forward;
                        Vector3 cameraRight = Camera.main.transform.right;

                        cameraForward.y = 0;
                        cameraRight.y = 0;

                        cameraForward.Normalize();
                        cameraRight.Normalize();

                        Vector3 movement = cameraRight * touch.deltaPosition.x * moveSpeed + cameraForward * touch.deltaPosition.y * moveSpeed;

                        transform.position += movement;
                    }
                    else if (touch.phase == TouchPhase.Ended)
                    {
                        child.SetActive(false);
                        camerascript.allow = true;
                        isTouchingObject = false;
                    }
                }
                else if (Input.touchCount == 2)
                {
                    Touch secondTouch = Input.GetTouch(1);

                    if (secondTouch.phase == TouchPhase.Moved)
                    {
                        transform.Rotate(0, secondTouch.deltaPosition.x * rotationSpeed, 0);
                    }
                    else if (secondTouch.phase == TouchPhase.Ended)
                    {
                        child.SetActive(false);
                        camerascript.allow = true;
                        isTouchingObject = false;
                    }
                }
            }
        }
    }

}
