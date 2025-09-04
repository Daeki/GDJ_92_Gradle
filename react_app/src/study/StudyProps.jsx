// age="20" user="winter"

// export default function StudyProps (props){
// console.log(props.age)

//export default function StudyProps ({age, user}){

// m = {age:20, user="iu"}

// export default function StudyProps (props){   
//     console.log(props.m.age)
//     console.log(props.m.user)

export default function StudyProps ({m}){
    console.log(m.age)
    console.log(m.user)

    return(
        <>
            <h1>Props page</h1>
        </>
    )

}