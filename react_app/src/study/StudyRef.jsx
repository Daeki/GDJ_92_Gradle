import { useRef, useState } from "react"

function StudyRef(){
    const [count, setCount]=useState(0)

    const age=useRef(0)

    function increase(){
        setCount(count+1);
    }

    function next(){
        age.current=3;
        console.log(age.current)
    }

    return (
        <>
            <h1>REF</h1>
            <h1>{count}</h1>
            <h1>{age.current}</h1>
            <button onClick={increase}>click</button>
            <button onClick={next}>REF</button>
        </>
    )
}

export default StudyRef