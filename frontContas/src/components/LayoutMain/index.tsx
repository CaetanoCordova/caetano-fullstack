import { Outlet } from "react-router-dom";
import Header from "../header";
import Sidebar from "../sidebar";
import Footer from "../footer";
import { useAuth } from "../hooks/useAuth";
import Headeradm from "../header/headerAdm";
import Sidebaradm from "../sidebar/sidebarAdm";

function LayoutMain() {
      const { role } = useAuth();

    const HeaderComponent = role === 'ROLE_ADMIN' ? Headeradm : Header;
    
    const SidebarComponent = role === 'ROLE_ADMIN' ? Sidebaradm : Sidebar;
    
  return (
    <div className="d-flex flex-column vh-100">
      <HeaderComponent />
      <div className="d-flex flex-grow-1">
        <SidebarComponent />
        <main className="flex-grow-1 p-3 bg-light">
          <Outlet />
        </main>
      </div>
      <Footer />
    </div>
  );
}

export default LayoutMain;
