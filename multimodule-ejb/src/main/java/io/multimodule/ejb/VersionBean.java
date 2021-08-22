package io.multimodule.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.mybatis.cdi.Transactional;

import io.multimodule.ejb.configs.ConfigsUtils;

/**
 *
 * @author madx
 */
@Stateless
@LocalBean
@Transactional
public class VersionBean {
	public String getVersion() {
		return "Version Name: \"" + ConfigsUtils.VERSION_NAME + "\"\n" + "Build Type: \"" + ConfigsUtils.BUILD_TYPE + "\"";
	}
}
