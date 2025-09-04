import { useLocation, useParams, useSearchParams } from "react-router-dom";

function StudyParam(){
    // url?     => 쿼리스트링
    // url/pa/  => 파라미터(RestFul)

    //1. urlSearchParam 쿼리스트링 OK, RestFul X
    const [param, setParam] = useSearchParams()

    console.log(param.get("num"))
    console.log(param.get("name"))

    //2. useParam  쿼리스트링 X , RestFul Ok
    const {num, name} = useParams();
    console.log(num)
    console.log(name)

    //또는
    console.log(useParams().num)
    console.log(useParams().name)

    //3. useLocation 쿼리스트링 Ok, Restful X
    const loc = useLocation();

    console.log(loc)
    console.log(loc.search)

    const us = new URLSearchParams(loc.search);
    console.log(us.get("num"))
    console.log(us.get("name"))

    //4. state
    // const loc = useLocation()
    if(loc.state != null){
        console.log(loc.state.age)
        console.log(loc.state.user)
    }
    return(
        <>
            <h1>Study Param</h1>
        </>
    )
}

export default StudyParam;