import React, {Component} from "react";
import CircularProgress from "material-ui/CircularProgress";

type State = {
    animating: boolean;
};

type Props = {
    onPress:Function,
    isVisible: boolean
};

export default class ProgressView extends Component {
    props: Props;

    static defaultProps = {
        isVisible: true,
    };

    state: State = {
        animating: true,
    };

    _clickHandler = () => {
        if (this.props.onPress) {
            this.props.onPress();
        }
    };

    render() {
        if (!this.props.isVisible) {
            return <div/>;
        }
        return (
            <div
                style={styles.overlay}
            >
                <div style={styles.container}>
                    <CircularProgress style={styles.progress}/>
                    <div style={styles.loading}>正在加载...</div>
                </div>
            </div>
        );
    }
}

const styles = {
    overlay: {
        position: 'fixed',
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        backgroundColor: "rgba(0, 0, 0, 0.5)",
        width: "100%",
        height: "100%",
        willChange: "opacity",
        transform: "translateZ(0px)",
        transition: "left 0ms cubic-bezier(0.23, 1, 0.32, 1) 0ms, opacity 400ms cubic-bezier(0.23, 1, 0.32, 1) 0ms",
        zIndex: 1400
    },
    container: {
        position: 'absolute',
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        borderRadius: 16,
        backgroundColor: 'rgba(0, 0, 0, 0.5)',
        margin: "auto",
        padding: "10px 0 10px 0",
        height: "100px",
        width: "120px",
        textAlign: "center",
    },
    spinner: {},
    progress: {
        margin: "10px auto"
    },
    loading: {
        margin: "10px auto",
        fontSize: 10,
        width: "70px",
        color: "#ffffff"
    }
};