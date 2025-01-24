const base = {
    get() {
        return {
            url : "http://localhost:8080/lvyouwangguanli/",
            name: "lvyouwangguanli",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/lvyouwangguanli/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "旅游网管理系统"
        } 
    }
}
export default base
