import { useNavigate } from "react-router-dom"

export default function Logout(){

    sessionStorage.removeItem("accesstoken")
    localStorage.removeItem("accesstoken")

    const nav= useNavigate()
    nav("/")

}