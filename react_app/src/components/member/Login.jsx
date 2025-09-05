import { useNavigate } from "react-router-dom";

export default function Login(){

    const nav = useNavigate()

    function login(e){
        e.preventDefault();

        const form = new FormData(e.target);

        let all = Object.fromEntries(form.entries())

        console.log(all)

        fetch("http://localhost/api/member/login", {
            method:"POST",
            body:form
        })
        //.then(r=>r.json())
        .then(r=>{
            const header = r.headers;
            console.log(header.get("Accesstoken"))
            localStorage.setItem("accesstoken", header.get("Accesstoken"))
            sessionStorage.setItem("accesstoken", header.get("Accesstoken"))
            nav("/")
        })
        .catch(e=>console.log("ppp ", e))


    }

    return(
        <>
        <h1>Login Page</h1>
        <form onSubmit={login}>
            <div>
                <input type="text" name="username"></input>
                <input type="password" name="password"></input>
                <button>Login</button>
            </div>
        </form>
        </>
    )
}