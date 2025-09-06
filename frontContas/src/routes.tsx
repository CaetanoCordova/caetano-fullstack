import Header from "./components/header/index";
import Footer from "./components/header/index";

import Home from "./paginas/home";
import Sidebar from "./components/sidebar/index";

function AppRoutes(){
    return(
        <>
            <Header/>
            <div className="d-flex">
                <Sidebar/>
                <div className="flex-grow1 p-4">
                    <Home/>
                </div>
            </div>
            <Footer/>
        
        </>
    );
}

export default AppRoutes;    