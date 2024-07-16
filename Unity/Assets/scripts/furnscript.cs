using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class furnscript : MonoBehaviour
{
    public float moveSpeed = 0.01f;
    public float rotationSpeed = 0.01f;
    private bool delete = false ;
    private Touch touch;
    private GameObject child1;
    private GameObject child2;

    private Vector3 initialPosition;

    private Renderer currentObjectRenderer;
    [SerializeField] private Material deleteMat;
    [SerializeField] private Material currentObjectMaterial;

    private bool isTouchingObject = false;
    public string staticObjectLayerName = "Wall";
    [SerializeField] private Rigidbody _rigidbody;

    void Start()
    {
        child1 = transform.GetChild(0).gameObject;
        child2 = transform.GetChild(1).gameObject;
        initialPosition = transform.position;

        currentObjectRenderer = GetComponent<Renderer>();

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
                        child1.SetActive(true);
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
                        child1.SetActive(false);
                        camerascript.allow = true;
                        isTouchingObject = false;
                        if(delete){
                            transform.position = initialPosition;
                            gameObject.SetActive(false);
                            RevertColor();
                        }
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
                        child1.SetActive(false);
                        camerascript.allow = true;
                        isTouchingObject = false;
                    }
                }
            }
        }
    }

    private void OnCollisionEnter(Collision collision){
        Debug.Log("yesssss" + collision.gameObject.tag);
        int collidedObjectLayer = collision.gameObject.layer;

        if(collidedObjectLayer == LayerMask.NameToLayer(staticObjectLayerName)){
            _rigidbody.isKinematic = true;

        }
        
        
    }

    private void OnTriggerEnter(Collider collision){
        if (collision.gameObject.tag == "trash"){
            _rigidbody.isKinematic = true;

            foreach (Transform grandchild in child2.transform)
            {
                Renderer grandchildRenderer = grandchild.GetComponent<Renderer>();
                if (grandchildRenderer != null)
                {
                    grandchildRenderer.material = null;
                    grandchildRenderer.material = deleteMat;
                }
            }
            delete = true;
        }
    }

    private void OnTriggerExit(Collider collision){
        if (collision.gameObject.tag == "trash"){
            RevertColor();
            delete = false;
        }    
    }

    private void RevertColor(){
        _rigidbody.isKinematic = false;
        foreach (Transform grandchild in child2.transform)
            {
                Renderer grandchildRenderer = grandchild.GetComponent<Renderer>();
                if (grandchildRenderer != null)
                {
                    grandchildRenderer.material = null;
                    grandchildRenderer.material = currentObjectMaterial;
                }
            }
    }

    private void OnCollisionExit(Collision collision){
        
        int collidedObjectLayer = collision.gameObject.layer;

        if(collidedObjectLayer == LayerMask.NameToLayer(staticObjectLayerName)){
            _rigidbody.isKinematic = false;
            
        }

 
    }


}
