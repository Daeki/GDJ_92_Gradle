import { useReducer, useRef, useState } from "react"
import { useNavigate } from "react-router-dom"

function Add(){

    const nav = useNavigate();

    const [files, setFiles]=useState([])
    const fileIdx = useRef(0);


    const [add, setAdd]= useState({
        boardTitle:"",
        boardWriter:"",
        boardContents:""
    })

    function inputChange(e){

        setAdd((prevState)=>({
            ...prevState,
            [e.target.name]:e.target.value
        }))
       
    }


    const title = useRef("title")
    const writer = useRef("writer")
    const contents = useRef("contents")

    function get(){
        const params = new URLSearchParams();
        params.append("boardTitle", add.boardTitle);
        params.append("boardWriter", add.boardWriter);
        params.append("boardContents", add.boardContents);

        fetch("http://localhost/notice", {
            method:"POST",
            body:params

        })
        .then(r=>r.json())
        .then(r=>console.log(r))
    }

    function get2(){
        console.log("title : ", title.current.value)
        console.log("writer : ", title.current.value)
        console.log("contents : ", title.current.value)
                const params = new URLSearchParams();
        params.append("boardTitle", title.current.value);
        params.append("boardWriter", writer.current.value);
        params.append("boardContents", contents.current.value);

        fetch("http://localhost/notice", {
            method:"POST",
            body:params

        })
        .then(r=>r.json())
        .then(r=>console.log(r))
    }

    function send(e){
        e.preventDefault();
        const formdata = new FormData(e.target) //<form></form>
        fetch("http://localhost/api/notice", {
            method:"POST",
            body:formdata,
            headers: {
                
            }
            
        })
        .then(r=>r.json())
        .then(r=>{
            if(r==true){
                console.log("등록")
                nav("/notice/list");
            }
        })
    }

    function addFile(){
        if(files.length>4){
            alert('최대 5개 까지 가능')
            return
        }

        const file = 
            <div key={fileIdx.current} data-file-idx={fileIdx.current}>
                <input type="file" name="attaches"></input>
                {/* <button type="button"  id={fileIdx.current} onClick={() => deleteFile(file.key)}>X</button> */}
                <button type="button"  id={fileIdx.current} onClick={deleteFile}>X</button>
            </div>
       
        
        fileIdx.current=fileIdx.current+1;

        const newFiles = [...files]
        newFiles.push(file)
        
        setFiles(newFiles)
    }
    // function deleteFile(key){
    function deleteFile(event){
     //setFiles((prev) => [...prev].filter(file => file.key != key)); // 상태 업데이트
        setFiles((prev) => {
                const thisIdx = event.target.parentElement.getAttribute("data-file-idx")
                let newFiles = []

                prev.forEach((file) => {
                    if (file.props["data-file-idx"] != thisIdx) {
                    newFiles.push(file)
                    }
                })
    
                return newFiles
            })
    }

    return(
        <>
            <h1>Add Page</h1>
           <form onSubmit={send}>
                <input type="text" name="boardTitle" ref={title} onChange={inputChange}></input>
                <input type="text" name="boardWriter" ref={writer} onChange={inputChange}></input>
                <textarea name="boardContents" ref={contents} onChange={inputChange}></textarea>

                <div>
                   {files}

                </div>
                <div>
                    <button type="button" onClick={addFile}>Add File</button>
                </div>
                <button type="button" onClick={get}>USESTATE</button>
                <button type="button" onClick={get2}>USEREF</button>
                <button>USEFORM</button>
            </form>
        </>
    )
}

export default Add