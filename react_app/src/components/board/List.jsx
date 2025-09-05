import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function List(){

    const [boards, setBoards] = useState([]);
    const [page, setPage]=useState(0);

    useEffect(()=>{
        fetch(`http://localhost/api/notice?page=${page}`, {
            method:"GET",
            headers: {
                Authorization: "Bearer "+sessionStorage.getItem("accesstoken")
            }
        })
            .then(r => r.json())
            .then(r => {
                console.log(r)
                
                const b = r.content.map(v=>
                    <li key={v.boardNum}>{v.boardTitle}</li>
                )
                setBoards(b);
    
            } 
            );

    }, [page])

    function next(){
        setPage(page+1);
    }    

    return(
        <>
            <h1>List Page</h1>
           
            <ul>
                {boards}
            </ul>
            <div>
                <h3>Page: {page}</h3>
                <button onClick={next}>NEXT</button>
            </div>
            <div>
                <Link to="/notice/add">Notice Add</Link>
            </div>
        </>
    )
}

export default List;